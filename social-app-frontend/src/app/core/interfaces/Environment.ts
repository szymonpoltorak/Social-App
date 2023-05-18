/**
 * Represents the environment configuration.
 */
export interface Environment {
    /**
     * The HTTP backend URL.
     */
    httpBackend: string;

    /**
     * Indicates whether the application is in production mode.
     */
    production: boolean;
}
