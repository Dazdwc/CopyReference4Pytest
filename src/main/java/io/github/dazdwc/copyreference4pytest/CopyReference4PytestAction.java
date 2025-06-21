package io.github.dazdwc.copyreference4pytest;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.ide.CopyPasteManager;
import io.github.dazdwc.copyreference4pytest.utils.PythonReferenceTranslator;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

public class CopyReference4PytestAction extends AnAction {

    public CopyReference4PytestAction() {
        super("Copy Reference for Pytest");
    }
    @Override
    public void actionPerformed(com.intellij.openapi.actionSystem.AnActionEvent e) {

        ActionManager actionManager = ActionManager.getInstance();

        AnAction copyReferenceAction = actionManager.getAction("CopyReference");

        if (copyReferenceAction != null) {
            copyReferenceAction.actionPerformed(e);

            // Obtener el contenido del portapapeles
            CopyPasteManager copyPasteManager = CopyPasteManager.getInstance();
            String clipboardContent = copyPasteManager.getContents(DataFlavor.stringFlavor);

            if (clipboardContent != null) {
                PythonReferenceTranslator translator = new PythonReferenceTranslator();
                String translatedPath = translator.identifyPathType(clipboardContent);

                // Copiar al portapapeles
                StringSelection stringSelection = new StringSelection(translatedPath);
                CopyPasteManager.getInstance().setContents(stringSelection);
            }

        }
    }

}