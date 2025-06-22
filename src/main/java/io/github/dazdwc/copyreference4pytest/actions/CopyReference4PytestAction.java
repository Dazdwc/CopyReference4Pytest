package io.github.dazdwc.copyreference4pytest.actions;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.ide.CopyPasteManager;
import io.github.dazdwc.copyreference4pytest.utils.CopiedPathAnalyzer;
import io.github.dazdwc.copyreference4pytest.utils.PytestPathFormatter;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import com.intellij.icons.AllIcons;

public class CopyReference4PytestAction extends AnAction {

    public CopyReference4PytestAction() {
        super("Convert to Pytest", "Convert PyCharm reference to pytest path", AllIcons.Actions.DiffWithClipboard);
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
                CopiedPathAnalyzer translator = new CopiedPathAnalyzer();
                PytestPathFormatter.PathType type = translator.identifyPathType(clipboardContent);

                String translatedPath = PytestPathFormatter.convert(clipboardContent, type);

                // Copiar al portapapeles
                StringSelection stringSelection = new StringSelection(translatedPath);
                CopyPasteManager.getInstance().setContents(stringSelection);
            }

        }
    }

}