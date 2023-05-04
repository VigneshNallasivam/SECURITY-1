package com.spring.basics.security;

import com.spring.basics.exception.JWTSecurityException;
import com.spring.basics.model.RefreshToken;
import com.spring.basics.repository.EmployeeRepository;
import com.spring.basics.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService
{

    @Value("${springjwt.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private EmployeeRepository empRepo;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(int empId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setEmployee(empRepo.findById(empId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token)
    {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new JWTSecurityException(token.getToken(),"Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(int empId)
    {
        return refreshTokenRepository.deleteByEmployee(empRepo.findById(empId).get());
    }
}