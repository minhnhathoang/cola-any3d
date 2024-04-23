package auto

-generated.entity;

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
@TableName("image_target")
public class ImageTargetDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String contentId;

    private String name;

    private String type;

    private String arSdk;
}
