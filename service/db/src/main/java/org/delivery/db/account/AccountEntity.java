package org.delivery.db.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
@SuperBuilder
@Entity
@Table(name = "account")
@Getter
@EqualsAndHashCode(callSuper = true)
public class AccountEntity extends BaseEntity {
}
