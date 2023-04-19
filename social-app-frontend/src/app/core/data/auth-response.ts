export class AuthResponse {
    authToken!: string;
    refreshToken!: string;

    isEmpty(): boolean {
        return this.authToken === "" || this.refreshToken === "";
    }
}
