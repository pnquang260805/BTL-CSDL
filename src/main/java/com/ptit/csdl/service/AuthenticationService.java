package com.ptit.csdl.service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import com.ptit.csdl.dto.request.creation.IntrospectRequest;
import com.ptit.csdl.dto.response.AuthenticationResponse;
import com.ptit.csdl.dto.response.IntrospectResponse;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jwt.JWTClaimsSet;
import com.ptit.csdl.dto.request.AuthenticationRequest;
import com.ptit.csdl.exception.ErrorCode;
import com.ptit.csdl.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

import com.ptit.csdl.exception.AppException;

@Service
@Slf4j
public class AuthenticationService {
    @Autowired
    CustomerRepository customerRepository;

	static Dotenv dotenv = Dotenv.load();

	private static final String SIGNER_KEY = dotenv.get("SIGNER_KEY");

    public AuthenticationResponse isMatches(AuthenticationRequest request) {
        var customer = customerRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTS.getMsg(), ErrorCode.CUSTOMER_NOT_EXISTS));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean authenticated = passwordEncoder.matches(request.getPassword(), customer.getPassword());
		if(!authenticated){
			throw new AppException(ErrorCode.UNAUTHENTICATED.getMsg(), ErrorCode.UNAUTHENTICATED);
		}

		//Sử dụng hàm tạo token ở dưới
		var token = generateToken(request.getEmail());

		return AuthenticationResponse.builder().token(token).authenticated(true).build();

	}
    
    private String generateToken(String email) {
    	JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

    	// Tạo claimset
    	JWTClaimsSet claimsSet = new JWTClaimsSet
    									.Builder()
    									.subject(email)
    									.expirationTime(new Date(Instant.now().plus(24, ChronoUnit.HOURS).toEpochMilli()))
    									.build();
    	// Tạo Payload
    	Payload payload = new Payload(claimsSet.toJSONObject());

    	JWSObject jwsObject = new JWSObject(header, payload);
    	// Sign
    	try {
			jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
			return jwsObject.serialize();
    	} catch (Exception e) {
			log.error(e.getMessage());
			throw(new RuntimeException(e));
		}
    }

	public IntrospectResponse IntroSpectToken(IntrospectRequest request) throws ParseException, JOSEException {
		var token = request.getToken();
		// Verify
		JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

		SignedJWT signedJWT = SignedJWT.parse(token);
		Date expTime = signedJWT.getJWTClaimsSet().getExpirationTime();
		var verified = signedJWT.verify(verifier);
		return IntrospectResponse.builder().valid(verified && expTime.after(new Date())).build();
	}
}
