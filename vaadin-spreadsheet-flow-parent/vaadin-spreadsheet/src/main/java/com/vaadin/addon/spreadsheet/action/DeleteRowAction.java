package com.vaadin.addon.spreadsheet.action;

/*
 * #%L
 * Vaadin Spreadsheet
 * %%
 * Copyright (C) 2013 - 2015 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file license.html distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <http://vaadin.com/license/cval-3>.
 * #L%
 */

import org.apache.poi.ss.util.CellRangeAddress;

import com.vaadin.addon.spreadsheet.Spreadsheet;
import com.vaadin.addon.spreadsheet.Spreadsheet.SelectionChangeEvent;

/**
 * Spreadsheet action for deleting a single row.
 * 
 * @author Vaadin Ltd.
 * @since 1.0
 */
@SuppressWarnings("serial")
public class DeleteRowAction extends SpreadsheetAction {

    public DeleteRowAction() {
        super("");
    }

    @Override
    public boolean isApplicableForSelection(Spreadsheet spreadsheet,
            SelectionChangeEvent event) {
        return false;
    }

    @Override
    public boolean isApplicableForHeader(Spreadsheet spreadsheet,
            CellRangeAddress headerRange) {
        if (!isSheetProtected(spreadsheet)) {
            if (headerRange.isFullRowRange()) {
                setCaption("Delete row " + (headerRange.getFirstRow() + 1));
                return true;
            }
        }
        return false;
    }

    @Override
    public void executeActionOnSelection(Spreadsheet spreadsheet,
            SelectionChangeEvent event) {
        throw new UnsupportedOperationException(
                "Delete row action can't be executed against a selection.");
    }

    @Override
    public void executeActionOnHeader(Spreadsheet spreadsheet,
            CellRangeAddress headerRange) {
        int rows = spreadsheet.getRows();
        int deletedRowIndex = headerRange.getFirstRow();
        if (deletedRowIndex + 1 > rows - 1) {
            // if removed last row, just delete it and make sheet smaller
            spreadsheet.deleteRows(deletedRowIndex, deletedRowIndex);
        } else {
            spreadsheet.shiftRows(deletedRowIndex + 1, (rows - 1), -1, true,
                    true);
        }
        spreadsheet.setMaxRows(rows - 1);
    }

}
