package org.austral.edu;

public class LexerExclusive extends LexerImpl{
    public LexerExclusive() {
        super(new TokenizerBetweenVersion());
    }
}
