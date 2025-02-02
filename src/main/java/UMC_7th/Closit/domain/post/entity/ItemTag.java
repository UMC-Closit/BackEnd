package UMC_7th.Closit.domain.post.entity;

import UMC_7th.Closit.domain.mission.entity.Mission;
import UMC_7th.Closit.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ItemTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false)
    private Double itemTagX;

    @Column(nullable = false)
    private Double itemTagY;

    @Column(nullable = false)
    private String tagType; // FRONT 또는 BACK 태그 구분

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
