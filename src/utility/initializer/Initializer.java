package utility.initializer;

/**
 *
 * @author Jordan
 */
public interface Initializer {
    public void initGridBagConstraints();
    void initJCompModelLoaders();
    void initRenderers();
    void initModels();
    void initViewComponents();
    void initControllers();
    void initDaoImpl();
}
