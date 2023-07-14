
package DataBase;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PModel {

    @SerializedName("connection")
    @Expose
    private Integer connection;
    @SerializedName("productaddd")
    @Expose
    private Integer result;

    public Integer getConnection() {
        return connection;
    }

    public void setConnection(Integer connection) {
        this.connection = connection;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "PModel{" +
                "connection=" + connection +
                ", productaddd=" + result +
                '}';
    }
}
