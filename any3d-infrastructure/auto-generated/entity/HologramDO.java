package auto-generated.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author nhathm
 * @since 2024-04-06
 */
@Getter
@Setter
@TableName("hologram")
public class HologramDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long contentId;

    private String name;

    private String type;
}