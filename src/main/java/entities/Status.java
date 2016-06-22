package entities;

/**
 * Created by Ruslan Zhdan on 21.06.2016.
 */
public enum Status {
    AVAILABLE,
    ABSENT,
    EXPECTED;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
