/**
 * Created by mhossein on 4/10/2015.
 */
abstract class Animal {
    int name;
    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public abstract int getNumber(int num);

}
