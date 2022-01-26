package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseEntityAll is a Querydsl query type for BaseEntityAll
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseEntityAll extends EntityPathBase<BaseEntityAll> {

    private static final long serialVersionUID = -1789373845L;

    public static final QBaseEntityAll baseEntityAll = new QBaseEntityAll("baseEntityAll");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QBaseEntityAll(String variable) {
        super(BaseEntityAll.class, forVariable(variable));
    }

    public QBaseEntityAll(Path<? extends BaseEntityAll> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseEntityAll(PathMetadata metadata) {
        super(BaseEntityAll.class, metadata);
    }

}

