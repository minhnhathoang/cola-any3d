
package util;

import lombok.Getter;
import lombok.NonNull;

/**
 * 基本类型
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
public enum PrimitiveType {

    BOOLEAN(Boolean.class) {
        @Override
        public Object cast(String value) {
            return Boolean.parseBoolean(value);
        }
    },
    BYTE(Byte.class) {
        @Override
        public Object cast(String value) {
            return value == null ? (byte) 0 : Byte.parseByte(value);
        }
    },
    CHAR(Character.class) {
        @Override
        public Object cast(String value) {
            return value == null ? '\u0000' : value.charAt(0);
        }
    },
    DOUBLE(Double.class) {
        @Override
        public Object cast(String value) {
            return value == null ? 0.0d : Double.parseDouble(value);
        }
    },
    FLOAT(Float.class) {
        @Override
        public Object cast(String value) {
            ;
            return value == null ? 0.0f : Float.parseFloat(value);
        }
    },
    INTEGER(Integer.class) {
        @Override
        public Object cast(String value) {
            return value == null ? 0 : Integer.parseInt(value);
        }
    },
    LONG(Long.class) {
        @Override
        public Object cast(String value) {
            return value == null ? 0L : Long.parseLong(value);
        }
    },
    SHORT(Short.class) {
        @Override
        public Object cast(String value) {
            return value == null ? (short) 0 : Short.parseShort(value);
        }
    };

    @Getter
    private final Class<?> wrapperClass;

    public abstract Object cast(String value);

    PrimitiveType(Class<?> wrapperClass) {
        this.wrapperClass = wrapperClass;
    }

    public static PrimitiveType parse(@NonNull String type) {
        for (PrimitiveType primitiveType : PrimitiveType.values()) {
            if (primitiveType.name().equalsIgnoreCase(type)) {
                return primitiveType;
            }
        }
        return null;
    }
}
