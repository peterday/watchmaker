//=============================================================================
// Copyright 2006-2010 Daniel W. Dyer
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//=============================================================================
package org.uncommons.watchmaker.impl.de.factory;

import org.uncommons.maths.number.ConstantGenerator;
import org.uncommons.maths.number.NumberGenerator;

/**
 * @author Peter Day
 */
public class NumberLimit<T extends Number> implements Limit<T> {
     NumberGenerator<T> minGenerator;
     NumberGenerator<T> maxGenerator;
    
    public NumberLimit(NumberGenerator<T> minValue, NumberGenerator<T> maxValue) {
        this.minGenerator = minValue;
        this.maxGenerator = maxValue;
    }

    public NumberLimit(T minValue, T maxValue) {
        this (new ConstantGenerator<T>(minValue), new ConstantGenerator<T>(maxValue));
    }


    public T getMinimumValue() {
       return minGenerator.nextValue();
   }
    

   public T getMaximumValue() {
           return maxGenerator.nextValue();
   }

    public boolean isValid(T value) {
        return value.doubleValue() <= getMaximumValue().doubleValue() && value.doubleValue() >= getMinimumValue().doubleValue();
    }

    public T limit(T value) {
        if (isValid(value)) {
            return value;
        }
        if (value.doubleValue() > getMaximumValue().doubleValue()) {
            return getMaximumValue();
        }
        return getMinimumValue();
    }


}
