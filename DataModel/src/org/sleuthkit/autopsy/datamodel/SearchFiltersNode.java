/*
 * Autopsy Forensic Browser
 * 
 * Copyright 2011 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.datamodel;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Sheet;
import org.openide.util.lookup.Lookups;
import org.sleuthkit.datamodel.SleuthkitCase;

/**
 * Node for search filter
 */
public class SearchFiltersNode extends AbstractNode implements DisplayableItemNode {

    private static final String FNAME = "File Types";
    private static final String DNAME = "Documents";
    SleuthkitCase skCase;

    SearchFiltersNode(SleuthkitCase skCase, boolean root) {
        super(Children.create(new SearchFiltersChildren(skCase, root), true), Lookups.singleton(root ? FNAME : DNAME));
        if (root) {
            super.setName(FNAME);
            super.setDisplayName(FNAME);
        } else {
            super.setName(DNAME);
            super.setDisplayName(DNAME);
        }
        this.skCase = skCase;
        this.setIconBaseWithExtension("org/sleuthkit/autopsy/images/file_types.png");
    }

    @Override
    public <T> T accept(DisplayableItemNodeVisitor<T> v) {
        return v.visit(this);
    }

    @Override
    protected Sheet createSheet() {
        Sheet s = super.createSheet();
        Sheet.Set ss = s.get(Sheet.PROPERTIES);
        if (ss == null) {
            ss = Sheet.createPropertiesSet();
            s.put(ss);
        }

        ss.put(new NodeProperty("Name",
                "Name",
                "no description",
                getName()));
        return s;
    }
}
