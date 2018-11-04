package lenovo.example.com.app1103;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author：shashe
 * 日期：2018/11/3
 */
@Entity
public class CacheCar {
    @Id(autoincrement = true)
    private Long id;
    private String data;
    @Generated(hash = 564086255)
    public CacheCar(Long id, String data) {
        this.id = id;
        this.data = data;
    }
    @Generated(hash = 1835476732)
    public CacheCar() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getData() {
        return this.data;
    }
    public void setData(String data) {
        this.data = data;
    }
    
}
