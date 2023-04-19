/**
 * Represents a registration request to be sent to the server.
 */
export class RegisterRequest {
    /**
     * The user's first name.
     */
    name!: string;

    /**
     * The user's last name.
     */
    surname!: string;

    /**
     * The user's email address.
     */
    email!: string;

    /**
     * The user's chosen password.
     */
    password!: string;

    /**
     * The user's date of birth.
     */
    dateOfBirth!: Date;
}
