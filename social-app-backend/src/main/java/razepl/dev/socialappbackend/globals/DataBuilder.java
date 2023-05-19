package razepl.dev.socialappbackend.globals;

/**
 * This interface defines a data builder for building entity data.
 *
 * @param <T> The type of entity data to build.
 */
public interface DataBuilder<T extends EntityData> {
    /**
     * Builds the entity data.
     *
     * @return The built entity data.
     */
    T buildData();
}
