package io.dingodb.exec.fun;

import io.dingodb.expr.runtime.ExprConfig;
import io.dingodb.expr.runtime.op.BinaryOp;
import io.dingodb.expr.runtime.op.OpKey;
import io.dingodb.expr.runtime.type.Type;
import io.dingodb.expr.runtime.type.Types;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public final class PowFunFactory extends PowFun {
    private static final long serialVersionUID = 6685412238645960303L;

    public static final PowFunFactory INSTANCE = new PowFunFactory();

    private final Map<Object, PowFun> opMap = new HashMap<>();

    private PowFunFactory() {
        super();
        opMap.put(keyOf(Types.DECIMAL, Types.DECIMAL), new PowDecimalDecimal());
        opMap.put(keyOf(Types.DOUBLE, Types.DOUBLE), new PowDoubleDouble());
    }

    @Override
    public BinaryOp getOp(OpKey key) {
        return opMap.get(key);
    }

    public static final class PowDecimalDecimal extends PowFun {
        private static final long serialVersionUID = -258960053260221496L;

        @Override
        protected BigDecimal evalNonNullValue(Object value0, Object value1, ExprConfig config) {
            return pow((BigDecimal) value0, (BigDecimal) value1);
        }

        @Override
        public Type getType() {
            return Types.DECIMAL;
        }

        @Override
        public OpKey getKey() {
            return keyOf(Types.DECIMAL, Types.DECIMAL);
        }
    }

    public static final class PowDoubleDouble extends PowFun {
        private static final long serialVersionUID = -5465917962531078387L;

        @Override
        protected Double evalNonNullValue(Object value0, Object value1, ExprConfig config) {
            return pow((Double) value0, (Double) value1);
        }

        @Override
        public Type getType() {
            return Types.DOUBLE;
        }

        @Override
        public OpKey getKey() {
            return keyOf(Types.DOUBLE, Types.DOUBLE);
        }
    }
}
