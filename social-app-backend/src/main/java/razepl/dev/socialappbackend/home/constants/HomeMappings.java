package razepl.dev.socialappbackend.home.constants;

/**
 * This class provides mappings for various endpoints related to the home page and user operations.
 */
public final class HomeMappings {
    /**
     * Base mapping for the home API.
     */
    public static final String HOME_MAPPING = "/api/home";

    /**
     * Mapping for retrieving user data.
     */
    public static final String USERDATA_MAPPING = "/userData";

    /**
     * Mapping for retrieving the list of friends.
     */
    public static final String FRIENDS_LIST_MAPPING = "/friendsList";

    /**
     * Mapping for updating GitHub data.
     */
    public static final String GITHUB_MAPPING = "/github";

    /**
     * Mapping for updating Twitter data.
     */
    public static final String TWITTER_MAPPING = "/twitter";

    /**
     * Mapping for updating LinkedIn data.
     */
    public static final String LINKEDIN_MAPPING = "/linkedin";

    /**
     * Mapping for updating job data.
     */
    public static final String JOB_MAPPING = "/job";

    /**
     * Mapping for updating location data.
     */
    public static final String LOCATION_MAPPING = "/location";

    /**
     * Base mapping for user-specific operations in the home API.
     */
    public static final String HOME_USER_MAPPING = "/api/home/user";

    /**
     * Mapping for adding a friend.
     */
    public static final String ADD_FRIEND_MAPPING = "/addFriend";

    /**
     * Mapping for removing a friend.
     */
    public static final String REMOVE_FRIEND_MAPPING = "/removeFriend";

    /**
     * Mapping for creating a new post.
     */
    public static final String CREATE_POST_MAPPING = "/createPost";

    /**
     * Mapping for retrieving the list of posts.
     */
    public static final String POST_LIST_MAPPING = "/postList";

    /**
     * Mapping for liking a post.
     */
    public static final String LIKE_POST_MAPPING = "/likePost";

    /**
     * Mapping for deleting a post.
     */
    public static final String DELETE_POST_MAPPING = "/deletePost";

    public static final String GET_COMMENTS_MAPPING = "/comments";

    public static final String CREATE_COMMENT_MAPPING = "/createComment";

    private HomeMappings() {
    }
}
