package com.github.peacetrue.vocabulary;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.MyBatis3DeleteModelAdapter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.MyBatis3UpdateModelAdapter;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

import javax.annotation.Generated;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static com.github.peacetrue.vocabulary.VocabularyDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;

@Mapper
public interface VocabularyMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "record.id")
    int insert(InsertStatementProvider<Vocabulary> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("VocabularyResult")
    Vocabulary selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "VocabularyResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "explanation", property = "explanation", jdbcType = JdbcType.VARCHAR),
    })
    List<Vocabulary> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(vocabulary);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, vocabulary);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, vocabulary)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteInPrimaryKey(Collection<Long> id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, vocabulary)
                .where(id, isIn(id_ instanceof List ? (List<Long>) id_ : new LinkedList<Long>(id_)))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Vocabulary record) {
        return insert(SqlBuilder.insert(record)
                .into(vocabulary)
                .map(id).toProperty("id")
                .map(name).toProperty("name")
                .map(explanation).toProperty("explanation")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Vocabulary record) {
        return insert(SqlBuilder.insert(record)
                .into(vocabulary)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(explanation).toPropertyWhenPresent("explanation", record::getExplanation)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Vocabulary>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, vocabulary.column("*"))
                .from(vocabulary);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Vocabulary>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, vocabulary.column("*"))
                .from(vocabulary);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Vocabulary selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, vocabulary.column("*"))
                .from(vocabulary)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Vocabulary record) {
        return UpdateDSL.updateWithMapper(this::update, vocabulary)
                .set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(explanation).equalTo(record::getExplanation)
                ;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Vocabulary record) {
        return UpdateDSL.updateWithMapper(this::update, vocabulary)
                .set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(explanation).equalToWhenPresent(record::getExplanation)
                ;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Vocabulary record) {
        return UpdateDSL.updateWithMapper(this::update, vocabulary)
                .set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(explanation).equalTo(record::getExplanation)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Vocabulary record) {
        return UpdateDSL.updateWithMapper(this::update, vocabulary)
                .set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(explanation).equalToWhenPresent(record::getExplanation)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }


    //append
    default List<Vocabulary> selectById(Collection<Long> ids) {
        return this.selectByExample().where(vocabulary.id, SqlBuilder.isIn(ids)).build().execute();
    }

}