package bis332.objects;

public class GeneInfoObject {

    private String geneid;
    private String symbol;
    private String synonyms;

    public GeneInfoObject(String geneid, String symbol, String synonyms) {
        this.geneid = geneid;
        this.symbol = symbol;
        this.synonyms = synonyms;
    }

    public String getGeneid() {
        return geneid;
    }

    public void setGeneid(String geneid) {
        this.geneid = geneid;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }
}
