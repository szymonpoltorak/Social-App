import { Observable } from "rxjs";
import { UserSearchData } from "@interfaces/home/UserSearchData";

/**
 * The interface for the SearchService.
 * Provides methods to retrieve a list of users based on a search pattern.
 */
export interface SearchServiceInterface {

    /**
     * Retrieves a list of users based on a search pattern.
     * @returns An Observable that emits an array of UserSearchData objects.
     */
    getListOfUsersOfPattern(): Observable<UserSearchData[]>;
}
