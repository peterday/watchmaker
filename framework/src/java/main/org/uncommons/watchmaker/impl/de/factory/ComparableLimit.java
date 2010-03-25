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
 * Simple class to record upper and lower bonds of Comparable items.
 * 
 * @param <T>
 */
public class ComparableLimit<T extends Comparable<T>> {
	private T minimumValue;
	private T maximumValue;
	
	
	public ComparableLimit(T minValue, T maxValue) {
		super();
		this.minimumValue = minValue;
		this.maximumValue = maxValue;
	}

    public T getMinimumValue() {
		return minimumValue;
	}
	public void setMinimumValue(T minimumValue) {
		this.minimumValue = minimumValue;
	}
	public T getMaximumValue() {
		return maximumValue;
	}
	public void setMaximumValue(T maximumValue) {
		this.maximumValue = maximumValue;
	}
	
	public T apply(T value) {
		 if (value.compareTo(minimumValue) < 0) {
			 return minimumValue;
		 }
		 if (value.compareTo(maximumValue) > 0) {
			 return maximumValue;
		 }
		 return value;
	}
	
	public boolean isValid(T value) {
		return (value.compareTo(minimumValue) >= 0) && (value.compareTo(maximumValue) <= 0);
	}
	
	
	
}
