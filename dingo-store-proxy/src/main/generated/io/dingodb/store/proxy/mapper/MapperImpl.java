package io.dingodb.store.proxy.mapper;

import io.dingodb.common.CommonId;
import io.dingodb.common.Coprocessor;
import io.dingodb.common.CoprocessorV2;
import io.dingodb.meta.entity.Column;
import io.dingodb.meta.entity.Table;
import io.dingodb.sdk.service.entity.common.Engine;
import io.dingodb.sdk.service.entity.common.KeyValue;
import io.dingodb.sdk.service.entity.common.Range;
import io.dingodb.sdk.service.entity.common.RangeWithOptions;
import io.dingodb.sdk.service.entity.common.ValueType;
import io.dingodb.sdk.service.entity.common.VectorScalardata;
import io.dingodb.sdk.service.entity.common.VectorTableData;
import io.dingodb.sdk.service.entity.common.VectorWithId;
import io.dingodb.sdk.service.entity.meta.ColumnDefinition;
import io.dingodb.sdk.service.entity.meta.DingoCommonId;
import io.dingodb.sdk.service.entity.meta.DropSchemaRequest;
import io.dingodb.sdk.service.entity.meta.GetSchemasRequest;
import io.dingodb.sdk.service.entity.meta.GetTableByNameRequest;
import io.dingodb.sdk.service.entity.meta.GetTablesBySchemaRequest;
import io.dingodb.sdk.service.entity.meta.TableDefinition;
import io.dingodb.sdk.service.entity.store.AggregationOperator;
import io.dingodb.sdk.service.entity.store.Context;
import io.dingodb.sdk.service.entity.store.LockExtraData;
import io.dingodb.sdk.service.entity.store.TxnBatchGetRequest;
import io.dingodb.sdk.service.entity.store.TxnBatchRollbackRequest;
import io.dingodb.sdk.service.entity.store.TxnCheckTxnStatusRequest;
import io.dingodb.sdk.service.entity.store.TxnCommitRequest;
import io.dingodb.sdk.service.entity.store.TxnPessimisticLockRequest;
import io.dingodb.sdk.service.entity.store.TxnPessimisticRollbackRequest;
import io.dingodb.sdk.service.entity.store.TxnPrewriteRequest;
import io.dingodb.sdk.service.entity.store.TxnResolveLockRequest;
import io.dingodb.sdk.service.entity.store.TxnScanRequest;
import io.dingodb.store.api.StoreInstance;
import io.dingodb.store.api.transaction.data.IsolationLevel;
import io.dingodb.store.api.transaction.data.Mutation;
import io.dingodb.store.api.transaction.data.ScalarValue;
import io.dingodb.store.api.transaction.data.Vector;
import io.dingodb.store.api.transaction.data.VectorScalarData;
import io.dingodb.store.api.transaction.data.checkstatus.TxnCheckStatus;
import io.dingodb.store.api.transaction.data.commit.TxnCommit;
import io.dingodb.store.api.transaction.data.pessimisticlock.TxnPessimisticLock;
import io.dingodb.store.api.transaction.data.prewrite.ForUpdateTsCheck;
import io.dingodb.store.api.transaction.data.prewrite.PessimisticCheck;
import io.dingodb.store.api.transaction.data.prewrite.TxnPreWrite;
import io.dingodb.store.api.transaction.data.resolvelock.TxnResolveLock;
import io.dingodb.store.api.transaction.data.rollback.TxnBatchRollBack;
import io.dingodb.store.api.transaction.data.rollback.TxnPessimisticRollBack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-29T05:39:13-0400",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 1.8.0_401 (Oracle Corporation)"
)
public class MapperImpl implements Mapper {

    @Override
    public TxnPrewriteRequest preWriteTo(TxnPreWrite preWrite) {
        if ( preWrite == null ) {
            return null;
        }

        TxnPrewriteRequest.TxnPrewriteRequestBuilder<?, ?> txnPrewriteRequest = TxnPrewriteRequest.builder();

        txnPrewriteRequest.context( txnPreWriteToContext( preWrite ) );
        txnPrewriteRequest.tryOnePc( preWrite.isTryOnePc() );
        txnPrewriteRequest.mutations( mutationListToMutationList( preWrite.getMutations() ) );
        txnPrewriteRequest.pessimisticChecks( pessimisticCheckListToPessimisticCheckList( preWrite.getPessimisticChecks() ) );
        byte[] primaryLock = preWrite.getPrimaryLock();
        if ( primaryLock != null ) {
            txnPrewriteRequest.primaryLock( Arrays.copyOf( primaryLock, primaryLock.length ) );
        }
        txnPrewriteRequest.maxCommitTs( preWrite.getMaxCommitTs() );
        txnPrewriteRequest.txnSize( preWrite.getTxnSize() );
        txnPrewriteRequest.lockTtl( preWrite.getLockTtl() );
        txnPrewriteRequest.startTs( preWrite.getStartTs() );
        txnPrewriteRequest.lockExtraDatas( lockExtraDataListToLockExtraDataList( preWrite.getLockExtraDatas() ) );
        txnPrewriteRequest.forUpdateTsChecks( forUpdateTsCheckListToForUpdateTsCheckList( preWrite.getForUpdateTsChecks() ) );

        return txnPrewriteRequest.build();
    }

    @Override
    public TxnCommitRequest commitTo(TxnCommit commit) {
        if ( commit == null ) {
            return null;
        }

        TxnCommitRequest.TxnCommitRequestBuilder<?, ?> txnCommitRequest = TxnCommitRequest.builder();

        txnCommitRequest.context( txnCommitToContext( commit ) );
        txnCommitRequest.startTs( commit.getStartTs() );
        txnCommitRequest.commitTs( commit.getCommitTs() );
        List<byte[]> list = commit.getKeys();
        if ( list != null ) {
            txnCommitRequest.keys( new ArrayList<byte[]>( list ) );
        }

        return txnCommitRequest.build();
    }

    @Override
    public TxnPessimisticLockRequest pessimisticLockTo(TxnPessimisticLock pessimisticLock) {
        if ( pessimisticLock == null ) {
            return null;
        }

        TxnPessimisticLockRequest.TxnPessimisticLockRequestBuilder<?, ?> txnPessimisticLockRequest = TxnPessimisticLockRequest.builder();

        txnPessimisticLockRequest.context( txnPessimisticLockToContext( pessimisticLock ) );
        txnPessimisticLockRequest.startTs( pessimisticLock.getStartTs() );
        txnPessimisticLockRequest.forUpdateTs( pessimisticLock.getForUpdateTs() );
        txnPessimisticLockRequest.mutations( mutationListToMutationList( pessimisticLock.getMutations() ) );
        byte[] primaryLock = pessimisticLock.getPrimaryLock();
        if ( primaryLock != null ) {
            txnPessimisticLockRequest.primaryLock( Arrays.copyOf( primaryLock, primaryLock.length ) );
        }
        txnPessimisticLockRequest.lockTtl( pessimisticLock.getLockTtl() );

        return txnPessimisticLockRequest.build();
    }

    @Override
    public TxnPessimisticRollbackRequest pessimisticRollBackTo(TxnPessimisticRollBack txnPessimisticRollBack) {
        if ( txnPessimisticRollBack == null ) {
            return null;
        }

        TxnPessimisticRollbackRequest.TxnPessimisticRollbackRequestBuilder<?, ?> txnPessimisticRollbackRequest = TxnPessimisticRollbackRequest.builder();

        txnPessimisticRollbackRequest.context( txnPessimisticRollBackToContext( txnPessimisticRollBack ) );
        txnPessimisticRollbackRequest.startTs( txnPessimisticRollBack.getStartTs() );
        txnPessimisticRollbackRequest.forUpdateTs( txnPessimisticRollBack.getForUpdateTs() );
        List<byte[]> list = txnPessimisticRollBack.getKeys();
        if ( list != null ) {
            txnPessimisticRollbackRequest.keys( new ArrayList<byte[]>( list ) );
        }

        return txnPessimisticRollbackRequest.build();
    }

    @Override
    public TxnBatchRollbackRequest rollbackTo(TxnBatchRollBack rollBack) {
        if ( rollBack == null ) {
            return null;
        }

        TxnBatchRollbackRequest.TxnBatchRollbackRequestBuilder<?, ?> txnBatchRollbackRequest = TxnBatchRollbackRequest.builder();

        txnBatchRollbackRequest.context( txnBatchRollBackToContext( rollBack ) );
        txnBatchRollbackRequest.startTs( rollBack.getStartTs() );
        List<byte[]> list = rollBack.getKeys();
        if ( list != null ) {
            txnBatchRollbackRequest.keys( new ArrayList<byte[]>( list ) );
        }

        return txnBatchRollbackRequest.build();
    }

    @Override
    public TxnScanRequest scanTo(long startTs, IsolationLevel isolationLevel, StoreInstance.Range range) {
        if ( isolationLevel == null && range == null ) {
            return null;
        }

        TxnScanRequest.TxnScanRequestBuilder<?, ?> txnScanRequest = TxnScanRequest.builder();

        txnScanRequest.startTs( startTs );
        txnScanRequest.context( isolationLevelToContext( isolationLevel ) );
        txnScanRequest.range( rangeToRangeWithOptions( range ) );

        return txnScanRequest.build();
    }

    @Override
    public TxnBatchGetRequest batchGetTo(long startTs, IsolationLevel isolationLevel, List<byte[]> keys) {
        if ( isolationLevel == null && keys == null ) {
            return null;
        }

        TxnBatchGetRequest.TxnBatchGetRequestBuilder<?, ?> txnBatchGetRequest = TxnBatchGetRequest.builder();

        txnBatchGetRequest.startTs( startTs );
        txnBatchGetRequest.context( isolationLevelToContext1( isolationLevel ) );
        List<byte[]> list = keys;
        if ( list != null ) {
            txnBatchGetRequest.keys( new ArrayList<byte[]>( list ) );
        }

        return txnBatchGetRequest.build();
    }

    @Override
    public TxnCheckTxnStatusRequest checkTxnTo(TxnCheckStatus txnCheck) {
        if ( txnCheck == null ) {
            return null;
        }

        TxnCheckTxnStatusRequest.TxnCheckTxnStatusRequestBuilder<?, ?> txnCheckTxnStatusRequest = TxnCheckTxnStatusRequest.builder();

        txnCheckTxnStatusRequest.callerStartTs( txnCheck.getCallerStartTs() );
        txnCheckTxnStatusRequest.currentTs( txnCheck.getCurrentTs() );
        txnCheckTxnStatusRequest.lockTs( txnCheck.getLockTs() );
        byte[] primaryKey = txnCheck.getPrimaryKey();
        if ( primaryKey != null ) {
            txnCheckTxnStatusRequest.primaryKey( Arrays.copyOf( primaryKey, primaryKey.length ) );
        }

        return txnCheckTxnStatusRequest.build();
    }

    @Override
    public TxnResolveLockRequest resolveTxnTo(TxnResolveLock txnResolve) {
        if ( txnResolve == null ) {
            return null;
        }

        TxnResolveLockRequest.TxnResolveLockRequestBuilder<?, ?> txnResolveLockRequest = TxnResolveLockRequest.builder();

        txnResolveLockRequest.context( txnResolveLockToContext( txnResolve ) );
        txnResolveLockRequest.startTs( txnResolve.getStartTs() );
        txnResolveLockRequest.commitTs( txnResolve.getCommitTs() );
        List<byte[]> list = txnResolve.getKeys();
        if ( list != null ) {
            txnResolveLockRequest.keys( new ArrayList<byte[]>( list ) );
        }

        return txnResolveLockRequest.build();
    }

    @Override
    public GetTablesBySchemaRequest getTablesBySchema(DingoCommonId schemaId) {
        if ( schemaId == null ) {
            return null;
        }

        GetTablesBySchemaRequest.GetTablesBySchemaRequestBuilder<?, ?> getTablesBySchemaRequest = GetTablesBySchemaRequest.builder();

        getTablesBySchemaRequest.ext$( toMap( schemaId.getExt$() ) );

        return getTablesBySchemaRequest.build();
    }

    @Override
    public GetTableByNameRequest getTableByName(DingoCommonId schemaId, String tableName) {
        if ( schemaId == null && tableName == null ) {
            return null;
        }

        GetTableByNameRequest.GetTableByNameRequestBuilder<?, ?> getTableByNameRequest = GetTableByNameRequest.builder();

        if ( schemaId != null ) {
            getTableByNameRequest.ext$( toMap( schemaId.getExt$() ) );
        }
        getTableByNameRequest.tableName( tableName );

        return getTableByNameRequest.build();
    }

    @Override
    public GetSchemasRequest getSchemas(DingoCommonId schemaId) {
        if ( schemaId == null ) {
            return null;
        }

        GetSchemasRequest.GetSchemasRequestBuilder<?, ?> getSchemasRequest = GetSchemasRequest.builder();

        getSchemasRequest.ext$( toMap( schemaId.getExt$() ) );

        return getSchemasRequest.build();
    }

    @Override
    public DropSchemaRequest dropSchema(DingoCommonId schemaId) {
        if ( schemaId == null ) {
            return null;
        }

        DropSchemaRequest.DropSchemaRequestBuilder<?, ?> dropSchemaRequest = DropSchemaRequest.builder();

        dropSchemaRequest.ext$( toMap( schemaId.getExt$() ) );

        return dropSchemaRequest.build();
    }

    @Override
    public Column columnFrom(ColumnDefinition columnDefinition) {
        if ( columnDefinition == null ) {
            return null;
        }

        Column.ColumnBuilder column = Column.builder();

        column.type( typeFrom( columnDefinition ) );
        column.primaryKeyIndex( columnDefinition.getIndexOfKey() );
        column.autoIncrement( columnDefinition.isAutoIncrement() );
        column.defaultValueExpr( columnDefinition.getDefaultVal() );
        column.sqlTypeName( columnDefinition.getSqlType() );
        column.elementTypeName( columnDefinition.getElementType() );
        column.name( columnDefinition.getName() );
        column.precision( columnDefinition.getPrecision() );
        column.scale( columnDefinition.getScale() );
        column.state( columnDefinition.getState() );
        column.comment( columnDefinition.getComment() );

        return column.build();
    }

    @Override
    public List<Column> columnsFrom(List<ColumnDefinition> columnDefinitions) {
        if ( columnDefinitions == null ) {
            return null;
        }

        List<Column> list = new ArrayList<Column>( columnDefinitions.size() );
        for ( ColumnDefinition columnDefinition : columnDefinitions ) {
            list.add( columnFrom( columnDefinition ) );
        }

        return list;
    }

    @Override
    public ColumnDefinition columnTo(io.dingodb.common.table.ColumnDefinition tableDefinition) {
        if ( tableDefinition == null ) {
            return null;
        }

        ColumnDefinition.ColumnDefinitionBuilder<?, ?> columnDefinition = ColumnDefinition.builder();

        columnDefinition.sqlType( tableDefinition.getTypeName() );
        columnDefinition.indexOfKey( tableDefinition.getPrimary() );
        columnDefinition.isAutoIncrement( tableDefinition.isAutoIncrement() );
        columnDefinition.defaultVal( tableDefinition.getDefaultValue() );
        columnDefinition.scale( tableDefinition.getScale() );
        columnDefinition.precision( tableDefinition.getPrecision() );
        columnDefinition.name( tableDefinition.getName() );
        columnDefinition.state( tableDefinition.getState() );
        columnDefinition.nullable( tableDefinition.isNullable() );
        columnDefinition.elementType( tableDefinition.getElementType() );
        columnDefinition.comment( tableDefinition.getComment() );

        return columnDefinition.build();
    }

    @Override
    public List<ColumnDefinition> columnsTo(List<io.dingodb.common.table.ColumnDefinition> tableDefinition) {
        if ( tableDefinition == null ) {
            return null;
        }

        List<ColumnDefinition> list = new ArrayList<ColumnDefinition>( tableDefinition.size() );
        for ( io.dingodb.common.table.ColumnDefinition columnDefinition : tableDefinition ) {
            list.add( columnTo( columnDefinition ) );
        }

        return list;
    }

    @Override
    public io.dingodb.common.table.ColumnDefinition columnDefinitionFrom(ColumnDefinition tableDefinition) {
        if ( tableDefinition == null ) {
            return null;
        }

        io.dingodb.common.table.ColumnDefinition.ColumnDefinitionBuilder columnDefinition = io.dingodb.common.table.ColumnDefinition.builder();

        columnDefinition.type( tableDefinition.getSqlType() );
        columnDefinition.primary( tableDefinition.getIndexOfKey() );
        columnDefinition.autoIncrement( tableDefinition.isAutoIncrement() );
        columnDefinition.defaultValue( tableDefinition.getDefaultVal() );
        columnDefinition.name( tableDefinition.getName() );
        columnDefinition.elementType( tableDefinition.getElementType() );
        columnDefinition.precision( tableDefinition.getPrecision() );
        columnDefinition.scale( tableDefinition.getScale() );
        columnDefinition.nullable( tableDefinition.isNullable() );
        columnDefinition.state( tableDefinition.getState() );
        columnDefinition.comment( tableDefinition.getComment() );

        return columnDefinition.build();
    }

    @Override
    public List<io.dingodb.common.table.ColumnDefinition> columnDefinitionFrom(List<ColumnDefinition> columnDefinitions) {
        if ( columnDefinitions == null ) {
            return null;
        }

        List<io.dingodb.common.table.ColumnDefinition> list = new ArrayList<io.dingodb.common.table.ColumnDefinition>( columnDefinitions.size() );
        for ( ColumnDefinition columnDefinition : columnDefinitions ) {
            list.add( columnDefinitionFrom( columnDefinition ) );
        }

        return list;
    }

    @Override
    public void tableFrom(TableDefinition tableDefinition, Table.TableBuilder builder) {
        if ( tableDefinition == null ) {
            return;
        }

        builder.columns( columnsFrom( tableDefinition.getColumns() ) );
        if ( tableDefinition.getEngine() != null ) {
            builder.engine( tableDefinition.getEngine().name() );
        }
        else {
            builder.engine( "TXN_LSM" );
        }
        builder.name( tableDefinition.getName() );
        builder.replica( tableDefinition.getReplica() );
        builder.version( tableDefinition.getVersion() );
        builder.properties( toMap( tableDefinition.getProperties() ) );
        builder.autoIncrement( tableDefinition.getAutoIncrement() );
        builder.charset( tableDefinition.getCharset() );
        builder.collate( tableDefinition.getCollate() );
        builder.tableType( tableDefinition.getTableType() );
        builder.rowFormat( tableDefinition.getRowFormat() );
        builder.comment( tableDefinition.getComment() );
        builder.createSql( tableDefinition.getCreateSql() );
    }

    @Override
    public TableDefinition tableTo(io.dingodb.common.table.TableDefinition tableDefinition) {
        if ( tableDefinition == null ) {
            return null;
        }

        TableDefinition.TableDefinitionBuilder<?, ?> tableDefinition1 = TableDefinition.builder();

        tableDefinition1.charset( tableDefinition.getCharset() );
        tableDefinition1.name( tableDefinition.getName() );
        tableDefinition1.version( tableDefinition.getVersion() );
        if ( tableDefinition.getEngine() != null ) {
            tableDefinition1.engine( Enum.valueOf( Engine.class, tableDefinition.getEngine() ) );
        }
        tableDefinition1.properties( mapping( tableDefinition.getProperties() ) );
        tableDefinition1.tableType( tableDefinition.getTableType() );
        tableDefinition1.collate( tableDefinition.getCollate() );
        tableDefinition1.ttl( tableDefinition.getTtl() );
        tableDefinition1.autoIncrement( tableDefinition.getAutoIncrement() );
        tableDefinition1.rowFormat( tableDefinition.getRowFormat() );
        tableDefinition1.createSql( tableDefinition.getCreateSql() );
        tableDefinition1.columns( columnsTo( tableDefinition.getColumns() ) );
        tableDefinition1.comment( tableDefinition.getComment() );
        tableDefinition1.replica( tableDefinition.getReplica() );

        return tableDefinition1.build();
    }

    @Override
    public CommonId copyId(CommonId id) {
        if ( id == null ) {
            return null;
        }

        CommonId.CommonType type = null;
        long domain = 0L;
        long seq = 0L;

        type = id.type;
        domain = id.domain;
        seq = id.seq;

        CommonId commonId = new CommonId( type, domain, seq );

        return commonId;
    }

    @Override
    public DingoCommonId copyId(DingoCommonId id) {
        if ( id == null ) {
            return null;
        }

        DingoCommonId.DingoCommonIdBuilder<?, ?> dingoCommonId = DingoCommonId.builder();

        dingoCommonId.entityType( id.getEntityType() );
        dingoCommonId.entityId( id.getEntityId() );
        dingoCommonId.parentEntityId( id.getParentEntityId() );
        dingoCommonId.ext$( toMap( id.getExt$() ) );

        return dingoCommonId.build();
    }

    @Override
    public List<CommonId> idFrom(Collection<DingoCommonId> ids) {
        if ( ids == null ) {
            return null;
        }

        List<CommonId> list = new ArrayList<CommonId>( ids.size() );
        for ( DingoCommonId dingoCommonId : ids ) {
            list.add( idFrom( dingoCommonId ) );
        }

        return list;
    }

    @Override
    public List<DingoCommonId> idTo(Collection<CommonId> ids) {
        if ( ids == null ) {
            return null;
        }

        List<DingoCommonId> list = new ArrayList<DingoCommonId>( ids.size() );
        for ( CommonId commonId : ids ) {
            list.add( idTo( commonId ) );
        }

        return list;
    }

    @Override
    public KeyValue kvTo(io.dingodb.common.store.KeyValue keyValue) {
        if ( keyValue == null ) {
            return null;
        }

        KeyValue.KeyValueBuilder<?, ?> keyValue1 = KeyValue.builder();

        byte[] key = keyValue.getKey();
        if ( key != null ) {
            keyValue1.key( Arrays.copyOf( key, key.length ) );
        }
        byte[] value = keyValue.getValue();
        if ( value != null ) {
            keyValue1.value( Arrays.copyOf( value, value.length ) );
        }

        return keyValue1.build();
    }

    @Override
    public io.dingodb.common.store.KeyValue kvFrom(KeyValue keyValue) {
        if ( keyValue == null ) {
            return null;
        }

        byte[] primaryKey = null;
        byte[] raw = null;

        io.dingodb.common.store.KeyValue keyValue1 = new io.dingodb.common.store.KeyValue( primaryKey, raw );

        byte[] key = keyValue.getKey();
        if ( key != null ) {
            keyValue1.setKey( Arrays.copyOf( key, key.length ) );
        }
        byte[] value = keyValue.getValue();
        if ( value != null ) {
            keyValue1.setValue( Arrays.copyOf( value, value.length ) );
        }

        return keyValue1;
    }

    @Override
    public Range copyRange(StoreInstance.Range range) {
        if ( range == null ) {
            return null;
        }

        Range.RangeBuilder<?, ?> range1 = Range.builder();

        return range1.build();
    }

    @Override
    public io.dingodb.sdk.service.entity.store.Coprocessor coprocessorTo(Coprocessor coprocessor) {
        if ( coprocessor == null ) {
            return null;
        }

        io.dingodb.sdk.service.entity.store.Coprocessor.CoprocessorBuilder<?, ?> coprocessor1 = io.dingodb.sdk.service.entity.store.Coprocessor.builder();

        List<Integer> list = coprocessor.getSelection();
        if ( list != null ) {
            coprocessor1.selectionColumns( new ArrayList<Integer>( list ) );
        }
        List<Integer> list1 = coprocessor.getGroupBy();
        if ( list1 != null ) {
            coprocessor1.groupByColumns( new ArrayList<Integer>( list1 ) );
        }
        coprocessor1.aggregationOperators( aggregationOperatorListToAggregationOperatorList( coprocessor.getAggregations() ) );
        coprocessor1.resultSchema( toSchemaWrapper( coprocessor.getResultSchema() ) );
        coprocessor1.schemaVersion( coprocessor.getSchemaVersion() );
        coprocessor1.originalSchema( toSchemaWrapper( coprocessor.getOriginalSchema() ) );
        byte[] expression = coprocessor.getExpression();
        if ( expression != null ) {
            coprocessor1.expression( Arrays.copyOf( expression, expression.length ) );
        }

        return coprocessor1.build();
    }

    @Override
    public io.dingodb.sdk.service.entity.common.CoprocessorV2 coprocessorTo(CoprocessorV2 coprocessor) {
        if ( coprocessor == null ) {
            return null;
        }

        io.dingodb.sdk.service.entity.common.CoprocessorV2.CoprocessorV2Builder<?, ?> coprocessorV2 = io.dingodb.sdk.service.entity.common.CoprocessorV2.builder();

        List<Integer> list = coprocessor.getSelection();
        if ( list != null ) {
            coprocessorV2.selectionColumns( new ArrayList<Integer>( list ) );
        }
        byte[] relExpr = coprocessor.getRelExpr();
        if ( relExpr != null ) {
            coprocessorV2.relExpr( Arrays.copyOf( relExpr, relExpr.length ) );
        }
        coprocessorV2.resultSchema( toSchemaWrapper1( coprocessor.getResultSchema() ) );
        coprocessorV2.schemaVersion( coprocessor.getSchemaVersion() );
        coprocessorV2.originalSchema( toSchemaWrapper1( coprocessor.getOriginalSchema() ) );

        return coprocessorV2.build();
    }

    protected io.dingodb.sdk.service.entity.store.IsolationLevel isolationLevelToIsolationLevel(IsolationLevel isolationLevel) {
        if ( isolationLevel == null ) {
            return null;
        }

        io.dingodb.sdk.service.entity.store.IsolationLevel isolationLevel1;

        switch ( isolationLevel ) {
            case InvalidIsolationLevel: isolationLevel1 = io.dingodb.sdk.service.entity.store.IsolationLevel.InvalidIsolationLevel;
            break;
            case SnapshotIsolation: isolationLevel1 = io.dingodb.sdk.service.entity.store.IsolationLevel.SnapshotIsolation;
            break;
            case ReadCommitted: isolationLevel1 = io.dingodb.sdk.service.entity.store.IsolationLevel.ReadCommitted;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + isolationLevel );
        }

        return isolationLevel1;
    }

    protected Context txnPreWriteToContext(TxnPreWrite txnPreWrite) {
        if ( txnPreWrite == null ) {
            return null;
        }

        Context.ContextBuilder<?, ?> context = Context.builder();

        context.isolationLevel( isolationLevelToIsolationLevel( txnPreWrite.getIsolationLevel() ) );

        return context.build();
    }

    protected VectorTableData vectorTableDataToVectorTableData(io.dingodb.store.api.transaction.data.VectorTableData vectorTableData) {
        if ( vectorTableData == null ) {
            return null;
        }

        VectorTableData.VectorTableDataBuilder<?, ?> vectorTableData1 = VectorTableData.builder();

        byte[] tableValue = vectorTableData.getTableValue();
        if ( tableValue != null ) {
            vectorTableData1.tableValue( Arrays.copyOf( tableValue, tableValue.length ) );
        }
        byte[] tableKey = vectorTableData.getTableKey();
        if ( tableKey != null ) {
            vectorTableData1.tableKey( Arrays.copyOf( tableKey, tableKey.length ) );
        }

        return vectorTableData1.build();
    }

    protected Map<String, io.dingodb.sdk.service.entity.common.ScalarValue> stringScalarValueMapToStringScalarValueMap(Map<String, ScalarValue> map) {
        if ( map == null ) {
            return null;
        }

        Map<String, io.dingodb.sdk.service.entity.common.ScalarValue> map1 = new LinkedHashMap<String, io.dingodb.sdk.service.entity.common.ScalarValue>( Math.max( (int) ( map.size() / .75f ) + 1, 16 ) );

        for ( java.util.Map.Entry<String, ScalarValue> entry : map.entrySet() ) {
            String key = entry.getKey();
            io.dingodb.sdk.service.entity.common.ScalarValue value = scalarValueTo( entry.getValue() );
            map1.put( key, value );
        }

        return map1;
    }

    protected VectorScalardata vectorScalarDataToVectorScalardata(VectorScalarData vectorScalarData) {
        if ( vectorScalarData == null ) {
            return null;
        }

        VectorScalardata.VectorScalardataBuilder<?, ?> vectorScalardata = VectorScalardata.builder();

        vectorScalardata.scalarData( stringScalarValueMapToStringScalarValueMap( vectorScalarData.getScalarData() ) );

        return vectorScalardata.build();
    }

    protected ValueType valueTypeToValueType(Vector.ValueType valueType) {
        if ( valueType == null ) {
            return null;
        }

        ValueType valueType1;

        switch ( valueType ) {
            case FLOAT: valueType1 = ValueType.FLOAT;
            break;
            case UINT8: valueType1 = ValueType.UINT8;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + valueType );
        }

        return valueType1;
    }

    protected io.dingodb.sdk.service.entity.common.Vector vectorToVector(Vector vector) {
        if ( vector == null ) {
            return null;
        }

        io.dingodb.sdk.service.entity.common.Vector.VectorBuilder<?, ?> vector1 = io.dingodb.sdk.service.entity.common.Vector.builder();

        vector1.dimension( vector.getDimension() );
        List<Float> list = vector.getFloatValues();
        if ( list != null ) {
            vector1.floatValues( new ArrayList<Float>( list ) );
        }
        List<byte[]> list1 = vector.getBinaryValues();
        if ( list1 != null ) {
            vector1.binaryValues( new ArrayList<byte[]>( list1 ) );
        }
        vector1.valueType( valueTypeToValueType( vector.getValueType() ) );

        return vector1.build();
    }

    protected VectorWithId vectorWithIdToVectorWithId(io.dingodb.store.api.transaction.data.VectorWithId vectorWithId) {
        if ( vectorWithId == null ) {
            return null;
        }

        VectorWithId.VectorWithIdBuilder<?, ?> vectorWithId1 = VectorWithId.builder();

        vectorWithId1.tableData( vectorTableDataToVectorTableData( vectorWithId.getTableData() ) );
        vectorWithId1.id( vectorWithId.getId() );
        vectorWithId1.scalarData( vectorScalarDataToVectorScalardata( vectorWithId.getScalarData() ) );
        vectorWithId1.vector( vectorToVector( vectorWithId.getVector() ) );

        return vectorWithId1.build();
    }

    protected io.dingodb.sdk.service.entity.store.Mutation mutationToMutation(Mutation mutation) {
        if ( mutation == null ) {
            return null;
        }

        io.dingodb.sdk.service.entity.store.Mutation.MutationBuilder<?, ?> mutation1 = io.dingodb.sdk.service.entity.store.Mutation.builder();

        mutation1.op( opTo( mutation.getOp() ) );
        byte[] key = mutation.getKey();
        if ( key != null ) {
            mutation1.key( Arrays.copyOf( key, key.length ) );
        }
        mutation1.vector( vectorWithIdToVectorWithId( mutation.getVector() ) );
        byte[] value = mutation.getValue();
        if ( value != null ) {
            mutation1.value( Arrays.copyOf( value, value.length ) );
        }

        return mutation1.build();
    }

    protected List<io.dingodb.sdk.service.entity.store.Mutation> mutationListToMutationList(List<Mutation> list) {
        if ( list == null ) {
            return null;
        }

        List<io.dingodb.sdk.service.entity.store.Mutation> list1 = new ArrayList<io.dingodb.sdk.service.entity.store.Mutation>( list.size() );
        for ( Mutation mutation : list ) {
            list1.add( mutationToMutation( mutation ) );
        }

        return list1;
    }

    protected io.dingodb.sdk.service.entity.store.PessimisticCheck pessimisticCheckToPessimisticCheck(PessimisticCheck pessimisticCheck) {
        if ( pessimisticCheck == null ) {
            return null;
        }

        io.dingodb.sdk.service.entity.store.PessimisticCheck pessimisticCheck1;

        switch ( pessimisticCheck ) {
            case SKIP_PESSIMISTIC_CHECK: pessimisticCheck1 = io.dingodb.sdk.service.entity.store.PessimisticCheck.SKIP_PESSIMISTIC_CHECK;
            break;
            case DO_PESSIMISTIC_CHECK: pessimisticCheck1 = io.dingodb.sdk.service.entity.store.PessimisticCheck.DO_PESSIMISTIC_CHECK;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + pessimisticCheck );
        }

        return pessimisticCheck1;
    }

    protected List<io.dingodb.sdk.service.entity.store.PessimisticCheck> pessimisticCheckListToPessimisticCheckList(List<PessimisticCheck> list) {
        if ( list == null ) {
            return null;
        }

        List<io.dingodb.sdk.service.entity.store.PessimisticCheck> list1 = new ArrayList<io.dingodb.sdk.service.entity.store.PessimisticCheck>( list.size() );
        for ( PessimisticCheck pessimisticCheck : list ) {
            list1.add( pessimisticCheckToPessimisticCheck( pessimisticCheck ) );
        }

        return list1;
    }

    protected LockExtraData lockExtraDataToLockExtraData(io.dingodb.store.api.transaction.data.prewrite.LockExtraData lockExtraData) {
        if ( lockExtraData == null ) {
            return null;
        }

        LockExtraData.LockExtraDataBuilder<?, ?> lockExtraData1 = LockExtraData.builder();

        byte[] extraData = lockExtraData.getExtraData();
        if ( extraData != null ) {
            lockExtraData1.extraData( Arrays.copyOf( extraData, extraData.length ) );
        }
        lockExtraData1.index( lockExtraData.getIndex() );

        return lockExtraData1.build();
    }

    protected List<LockExtraData> lockExtraDataListToLockExtraDataList(List<io.dingodb.store.api.transaction.data.prewrite.LockExtraData> list) {
        if ( list == null ) {
            return null;
        }

        List<LockExtraData> list1 = new ArrayList<LockExtraData>( list.size() );
        for ( io.dingodb.store.api.transaction.data.prewrite.LockExtraData lockExtraData : list ) {
            list1.add( lockExtraDataToLockExtraData( lockExtraData ) );
        }

        return list1;
    }

    protected io.dingodb.sdk.service.entity.store.ForUpdateTsCheck forUpdateTsCheckToForUpdateTsCheck(ForUpdateTsCheck forUpdateTsCheck) {
        if ( forUpdateTsCheck == null ) {
            return null;
        }

        io.dingodb.sdk.service.entity.store.ForUpdateTsCheck.ForUpdateTsCheckBuilder<?, ?> forUpdateTsCheck1 = io.dingodb.sdk.service.entity.store.ForUpdateTsCheck.builder();

        forUpdateTsCheck1.expectedForUpdateTs( forUpdateTsCheck.getExpectedForUpdateTs() );
        forUpdateTsCheck1.index( forUpdateTsCheck.getIndex() );

        return forUpdateTsCheck1.build();
    }

    protected List<io.dingodb.sdk.service.entity.store.ForUpdateTsCheck> forUpdateTsCheckListToForUpdateTsCheckList(List<ForUpdateTsCheck> list) {
        if ( list == null ) {
            return null;
        }

        List<io.dingodb.sdk.service.entity.store.ForUpdateTsCheck> list1 = new ArrayList<io.dingodb.sdk.service.entity.store.ForUpdateTsCheck>( list.size() );
        for ( ForUpdateTsCheck forUpdateTsCheck : list ) {
            list1.add( forUpdateTsCheckToForUpdateTsCheck( forUpdateTsCheck ) );
        }

        return list1;
    }

    protected Context txnCommitToContext(TxnCommit txnCommit) {
        if ( txnCommit == null ) {
            return null;
        }

        Context.ContextBuilder<?, ?> context = Context.builder();

        context.isolationLevel( isolationLevelToIsolationLevel( txnCommit.getIsolationLevel() ) );

        return context.build();
    }

    protected Context txnPessimisticLockToContext(TxnPessimisticLock txnPessimisticLock) {
        if ( txnPessimisticLock == null ) {
            return null;
        }

        Context.ContextBuilder<?, ?> context = Context.builder();

        context.isolationLevel( isolationLevelToIsolationLevel( txnPessimisticLock.getIsolationLevel() ) );

        return context.build();
    }

    protected Context txnPessimisticRollBackToContext(TxnPessimisticRollBack txnPessimisticRollBack) {
        if ( txnPessimisticRollBack == null ) {
            return null;
        }

        Context.ContextBuilder<?, ?> context = Context.builder();

        context.isolationLevel( isolationLevelToIsolationLevel( txnPessimisticRollBack.getIsolationLevel() ) );

        return context.build();
    }

    protected Context txnBatchRollBackToContext(TxnBatchRollBack txnBatchRollBack) {
        if ( txnBatchRollBack == null ) {
            return null;
        }

        Context.ContextBuilder<?, ?> context = Context.builder();

        context.isolationLevel( isolationLevelToIsolationLevel( txnBatchRollBack.getIsolationLevel() ) );

        return context.build();
    }

    protected Context isolationLevelToContext(IsolationLevel isolationLevel) {
        if ( isolationLevel == null ) {
            return null;
        }

        Context.ContextBuilder<?, ?> context = Context.builder();

        context.isolationLevel( isolationLevelToIsolationLevel( isolationLevel ) );

        return context.build();
    }

    protected Range rangeToRange(StoreInstance.Range range) {
        if ( range == null ) {
            return null;
        }

        Range.RangeBuilder<?, ?> range1 = Range.builder();

        byte[] startKey = range.start;
        if ( startKey != null ) {
            range1.startKey( Arrays.copyOf( startKey, startKey.length ) );
        }
        byte[] endKey = range.end;
        if ( endKey != null ) {
            range1.endKey( Arrays.copyOf( endKey, endKey.length ) );
        }

        return range1.build();
    }

    protected RangeWithOptions rangeToRangeWithOptions(StoreInstance.Range range) {
        if ( range == null ) {
            return null;
        }

        RangeWithOptions.RangeWithOptionsBuilder<?, ?> rangeWithOptions = RangeWithOptions.builder();

        rangeWithOptions.range( rangeToRange( range ) );
        rangeWithOptions.withStart( range.withStart );
        rangeWithOptions.withEnd( range.withEnd );

        return rangeWithOptions.build();
    }

    protected Context isolationLevelToContext1(IsolationLevel isolationLevel) {
        if ( isolationLevel == null ) {
            return null;
        }

        Context.ContextBuilder<?, ?> context = Context.builder();

        context.isolationLevel( isolationLevelToIsolationLevel( isolationLevel ) );

        return context.build();
    }

    protected Context txnResolveLockToContext(TxnResolveLock txnResolveLock) {
        if ( txnResolveLock == null ) {
            return null;
        }

        Context.ContextBuilder<?, ?> context = Context.builder();

        context.isolationLevel( isolationLevelToIsolationLevel( txnResolveLock.getIsolationLevel() ) );

        return context.build();
    }

    protected List<AggregationOperator> aggregationOperatorListToAggregationOperatorList(List<io.dingodb.common.AggregationOperator> list) {
        if ( list == null ) {
            return null;
        }

        List<AggregationOperator> list1 = new ArrayList<AggregationOperator>( list.size() );
        for ( io.dingodb.common.AggregationOperator aggregationOperator : list ) {
            list1.add( aggregationOperatorTo( aggregationOperator ) );
        }

        return list1;
    }
}
