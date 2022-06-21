pdflatex -synctex=1 -interaction=nonstopmode thesis.tex
bibtex thesis.aux
pdflatex -synctex=1 -interaction=nonstopmode thesis.tex
pdflatex -synctex=1 -interaction=nonstopmode thesis.tex
pdflatex thesis.tex
makeglossaries thesis
pdflatex thesis.tex