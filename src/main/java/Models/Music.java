package Models;

public class Music {
    private int Id;
    private String name;
    private String downloadLink;
    private boolean isAnalysed;
    private int rank;

    public Music(int id, String name, String downloadLink, boolean isAnalysed, int rank) {
        Id = id;
        this.name = name;
        this.downloadLink = downloadLink;
        this.isAnalysed = isAnalysed;
        this.rank = rank;
    }

}
