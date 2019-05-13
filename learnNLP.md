Speech and lanuage processing(3rd)
https://web.stanford.edu/~jurafsky/slp3/
with regards 
Dan Jurafsky and James H. Martin

课件地址： https://web.stanford.edu/~jurafsky/slp3/slides/LM_4.pdf
老师： dan jurafsky 
1.  language models
the chain rule:
P(A,B,C,D)	=	P(A)P(B|A)P(C|A,B)P(D|A,B,C)
with Markov Assumption
=> 
(1).    unigram model
(2).    bigram model
(3).    ...n-gram models


2.  n-gram models

count, normalize, result
e.g.
https://blog.csdn.net/baimafujinji/article/details/51281816

理论上，n越大越好，经验上，trigram用的最多，尽管如此，原则上，能用bigram解决，绝不使用trigram。

Language Modeling Toolkits
SRILM
•http://www.speech.sri.com/projects/srilm/
KenLM
• https://kheafield.com/code/kenlm/

3.  evaluation and perplexity
迷惑度越小，句子概率越大，语言模型越好
4.  smoothing

1). add-one(Laplace) smoothing

2). good-turing smoothing

3). interpolation smoothing

4). kneser-ney smoothing
理解不详：

