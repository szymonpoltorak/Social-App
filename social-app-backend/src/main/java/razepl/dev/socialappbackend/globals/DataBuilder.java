package razepl.dev.socialappbackend.globals;

public interface DataBuilder<T extends EntityData> {
    T buildData();
}
