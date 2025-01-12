package UMC_7th.Closit.domain.post.entity;

import UMC_7th.Closit.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostHashTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="hashtag_id", nullable = false)
    private HashTag hashTag;

    @ManyToOne
    @JoinColumn(name="post_id", nullable = false)
    private Post post;
}
