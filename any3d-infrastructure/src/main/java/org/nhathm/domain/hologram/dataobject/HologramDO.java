package org.nhathm.domain.hologram.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.nhathm.domain.content.dataobject.ContentDO;

import java.io.Serial;
import java.io.Serializable;

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
public class HologramDO {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField
    private String contentId;

    @TableField
    private String filename;
}
