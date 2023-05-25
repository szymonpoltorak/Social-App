/**
 * Represents user search data.
 */
export interface UserSearchData {
    /**
     * The username of the user.
     */
    username: string;

    /**
     * The job or profession of the user.
     */
    job: string;

    /**
     * The full name of the user.
     */
    fullName: string;

    /**
     * Indicates whether the user is a friend or not.
     */
    isUsersFriend: boolean;
}
