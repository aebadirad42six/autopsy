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
package org.sleuthkit.autopsy.directorytree;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.sleuthkit.autopsy.coreutils.Log;

/**
 *
 * @author jantonius
 */
class CollapseAction extends AbstractAction {

    CollapseAction(String title) {
        super(title);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Log.noteAction(this.getClass());

        ExplorerManager em = DirectoryTreeTopComponent.findInstance().getExplorerManager();
        Node[] selectedNode = em.getSelectedNodes();

        // Collapse all

        BeanTreeView tree = DirectoryTreeTopComponent.findInstance().getTree();
        collapseAll(tree, selectedNode[0]);
    }

    /**
     * Collapse all visible children of the given node on the given tree.
     *
     * @param tree          the given tree
     * @param currentNode   the current selectedNode
     */
    private void collapseAll(BeanTreeView tree, Node currentNode) {

        Children c = currentNode.getChildren();

        for (Node next : c.getNodes()) {
            if (tree.isExpanded(next)) {
                this.collapseAll(tree, next);
            }
        }

        tree.collapseNode(currentNode);
    }
}
