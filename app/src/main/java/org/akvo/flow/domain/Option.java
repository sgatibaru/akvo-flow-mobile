/*
 *  Copyright (C) 2010-2012 Stichting Akvo (Akvo Foundation)
 *
 *  This file is part of Akvo FLOW.
 *
 *  Akvo FLOW is free software: you can redistribute it and modify it under the terms of
 *  the GNU Affero General Public License (AGPL) as published by the Free Software Foundation,
 *  either version 3 of the License or any later version.
 *
 *  Akvo FLOW is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU Affero General Public License included below for more details.
 *
 *  The full license text can also be seen at <http://www.gnu.org/licenses/agpl.html>.
 */

package org.akvo.flow.domain;

import java.util.HashMap;

/**
 * simple data structure for representing question options.
 * 
 * @author Christopher Fagiani
 */
public class Option {
    private String text;
    private String code;
    private boolean isOther;
    private HashMap<String, AltText> altTextMap = new HashMap<>();

    public void addAltText(AltText altText) {
        altTextMap.put(altText.getLanguage(), altText);
    }

    public AltText getAltText(String lang) {
        return altTextMap.get(lang);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setIsOther(boolean isOther) {
        this.isOther = isOther;
    }

    public boolean isOther() {
        return isOther;
    }

    @Override
    public boolean equals(Object option) {
        if (option == null || !(option instanceof Option)) {
            return false;
        }

        return text != null && text.equals(((Option) option).getText());
    }

}
