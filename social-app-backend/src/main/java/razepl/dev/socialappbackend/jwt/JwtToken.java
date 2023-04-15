package razepl.dev.socialappbackend.jwt;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import razepl.dev.socialappbackend.jwt.interfaces.Token;
import razepl.dev.socialappbackend.user.User;

@Data
@Entity
@Table(name = "Tokens")
public class JwtToken implements Token {
    @NotNull
    @Id
    private long id;

    @NotNull
    @Column(unique = true)
    private String token;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
}
