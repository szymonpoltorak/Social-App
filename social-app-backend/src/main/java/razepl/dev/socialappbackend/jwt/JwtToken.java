package razepl.dev.socialappbackend.jwt;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import razepl.dev.socialappbackend.jwt.interfaces.Token;
import razepl.dev.socialappbackend.user.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tokens")
public class JwtToken implements Token {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tokenId;

    @NotNull
    @Column(unique = true)
    private String token;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isExpired;

    private boolean isRevoked;
}
