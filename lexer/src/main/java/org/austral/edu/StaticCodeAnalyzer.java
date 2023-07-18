package org.austral.edu;

import org.austral.edu.LexerImpl;
import org.austral.edu.Tokenizer;

public class StaticCodeAnalyzer extends LexerImpl {
    public StaticCodeAnalyzer() {
        super(new TokenizerAnalyzer());
    }
}
