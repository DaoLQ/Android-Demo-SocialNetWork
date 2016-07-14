package vn.fstyle.androiddemosocial.facebook.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * FriendListResponse.
 *
 * @author DaoLQ
 */
@Data
public class FriendListResponse {

    private PagingEntity paging;
    @SerializedName("data")
    private List<Friend> friends;

    @Data
    public static class PagingEntity {

        private CursorsEntity cursors;
        private String next;

        @Data
        public static class CursorsEntity {
            private String after;
            private String before;
        }
    }
}
