package riverway.domain;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.util.UUID;

@Entity
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Product product;

    public Attachment() {
    }

    public Attachment(String path, String originalName, String savedName, Long size, Product product) {
        this.path = path;
        this.originalName = originalName;
        this.savedName = savedName.trim();
        this.size = size;
        this.product = product;
    }

    public String getSavedName() {
        return savedName;
    }

    public File save() {
        return new File(path + File.separator + savedName);
    }

    public String getPath() {
        return String.format("/images/item/%s", savedName.trim());
    }

    public static Attachment of(String path, Product product, MultipartFile file) {
        String originalName = file.getOriginalFilename();
        return new Attachment(path, originalName, convert(originalName), file.getSize(), product);
    }

    public static String convert(String file) {
        return UUID.randomUUID().toString().replace("-", "") + parseExtention(file);
    }

    public static String parseExtention(String file) {
        return file.substring(file.lastIndexOf("."));
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", originalName='" + originalName + '\'' +
                ", savedName='" + savedName + '\'' +
                ", size=" + size +
                ", product=" + product.getId() +
                '}';
    }
}
