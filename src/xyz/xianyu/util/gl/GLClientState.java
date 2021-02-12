package xyz.xianyu.util.gl;

public enum GLClientState implements GLenum
{
    COLOR("COLOR", 0, "GL_COLOR_ARRAY", 32886), 
    EDGE("EDGE", 1, "GL_EDGE_FLAG_ARRAY", 32889), 
    FOG("FOG", 2, "GL_FOG_COORD_ARRAY", 33879), 
    INDEX("INDEX", 3, "GL_INDEX_ARRAY", 32887), 
    NORMAL("NORMAL", 4, "GL_NORMAL_ARRAY", 32885), 
    SECONDARY_COLOR("SECONDARY_COLOR", 5, "GL_SECONDARY_COLOR_ARRAY", 33886), 
    TEXTURE("TEXTURE", 6, "GL_TEXTURE_COORD_ARRAY", 32888), 
    VERTEX("VERTEX", 7, "GL_VERTEX_ARRAY", 32884);
    
    private final String name;
    private final int cap;
    
    private GLClientState(final String s, final int n, final String name, final int cap) {
        this.name = name;
        this.cap = cap;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public int getCap() {
        return this.cap;
    }
}
