public interface Hoverable {

    /**
     * [contains]
     * @param x the x position of the point (cursor)
     * @param y the y position of the point (cursor)
     * @return boolean, whether the point is within the selectable item's hitbox or not
     */
    public boolean contains(int x, int y);

}
