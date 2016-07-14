package vn.fstyle.androiddemosocial.facebook.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Friend.
 *
 * @author DaoLQ
 */
@Data
public class Friend {
    private PictureEntity picture;
    private String id;
    private String name;

    @Data
    public static class PictureEntity {

        private DataInside data;

        @Data
        public static class DataInside {
            private String url;
            @SerializedName("is_silhouette")
            private boolean isSilhouette;
        }
    }
}
