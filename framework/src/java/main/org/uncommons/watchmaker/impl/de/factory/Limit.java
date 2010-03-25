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

/**
 * implementations provide a simple method to hold minimum and maximum allowed values.
 * @author Peter Day
 */
public interface Limit<T> {

    /**
     * @return The minimum permitted value
     */
    public T getMinimumValue();

    /**
     *
     * @return The maximum allowed value
     */
    public T getMaximumValue();

    /**
     * Returns true if the value is >= minimumValue and <= maximumValue specified by
     * the limit.
     * @param value the value to test
     * @return
     */
    public boolean isValid(T value);

    /**
     * <p>Restricts the value to the limit specified by this object.</p>
     * <p>More specifically - concrete implementations should return:</p>
     * <code>
     *  value > getMaximumValue: maximumvalue
     *  value < minimumValue: minimumValue
     *  else: value
     * </code>     
     * @param value
     * @return
     */
    public T limit(T value);
}
