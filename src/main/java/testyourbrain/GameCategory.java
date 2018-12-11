package testyourbrain;

public enum GameCategory {

    POLITIK, GEOGRAPHIE, GESCHICHTE, SONSTIGES;

    public static GameCategory getByString(String category) {
        if (category.equals("Politik")) {
            return POLITIK;
        }
        if (category.equals("Sonstiges")) {
            return SONSTIGES;
        }
        if (category.equals("Geschichte")) {
            return GESCHICHTE;
        }
        if (category.equals("Geografie")) {
            return GEOGRAPHIE;
        }
        return null;
    }
}
