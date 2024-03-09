package org.delivery.db.storemenu;

import org.delivery.db.storemenu.enums.StoreMenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreMenuRepository extends JpaRepository<StoreMenuEntity,Long> {

    // 유효한 매뉴 체크
    // select * from store_menu where store_id =? and status =? order by id desc limit 1;
    Optional<StoreMenuEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreMenuStatus status);

    // 특정 가게의 매뉴 가져오기
    // select * from store_menu where store_id =? and status =? order by sequence desc;
    @Query("SELECT sm FROM StoreMenuEntity sm " +
            "WHERE sm.storeEntity.id = :storeId " +
            "AND sm.status = :status " +
            "ORDER BY sm.sequence DESC")
    List<StoreMenuEntity> findAllByStoreIdAndStatusOrderBySequenceDesc(@Param("storeId") Long storeId, @Param("status") StoreMenuStatus status);
}
