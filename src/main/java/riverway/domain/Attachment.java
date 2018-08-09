package riverway.domain;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Attachment {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String path;

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private String savedName;

    @Column
    private Long size;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Column(nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @Column(nullable = false)
    private User seller;

    public Attachment() {
    }

    public Attachment(String path, String originalName, String savedName, Long size, Product product) {
        this.path = path;
        this.originalName = originalName;
        this.savedName = savedName;
        this.size = size;
        this.product = product;
    }

    public static Attachment of(String path, User seller, Product product, MultipartFile file){
        String originalName = file.getOriginalFilename();
        return new Attachment(path, originalName, convert(originalName), file.getSize(), product);
    }

    public static String convert(String file){
        return UUID.randomUUID().toString().replace("-", "") + parseExtention(file);
    }

    public static String parseExtention(String file){
        return file.substring(file.lastIndexOf("."));
    }
}
