#include<stdio.h>
#include<stdlib.h>
#include<math.h>
#include<string.h>
#include<stdbool.h>


typedef struct node
{
	char charactere;
	struct node *left;
	struct node *right;
} node ;



int pop();
int precedence(char symbol);
int isEmpty();
void infix_to_prefix();
int checker(char symbol);
void push(long int symbol);
void negative();
char* get_rules_K(int index);
char prefix_string[20], infix_string[20], postfix_string[20] ,negative_prefix_string[20], string_form[20];
int top;
long int stack[20];
node* addNode(node **tree, char charactere,bool direction);
void printTree(node *tree);
void printReverseTree(node *tree);
void clearTree(node **tree);
    

// si direction faux alors noed de droite
node* addNode(node **tree, char charactere,bool direction )
{
    node *tmpNode;
    node *tmpTree = *tree;

    node *elem = malloc(sizeof(node));
    elem->charactere = charactere;
    elem->left = NULL;
    elem->right = NULL;

    if(tmpTree)
    do
    {
        tmpNode = tmpTree;
        if(direction == false )
        {
            tmpTree = tmpTree->right;
            if(!tmpTree) tmpNode->right = elem;
        }
        else
        {
            tmpTree = tmpTree->left;
            if(!tmpTree) tmpNode->left = elem;
        }
    }
    while(tmpTree);
    else  *tree = elem;
}

/***************************************************************************/

void printTree(node *tree)
{
    if(!tree) return;

    if(tree->left)  printTree(tree->left);

    printf("Cle = %c\n", tree->charactere);

    if(tree->right) printTree(tree->right);
}

/***************************************************************************/

void clearTree(node **tree)
{
    node *tmpTree = *tree;

    if(!tree) return;

    if(tmpTree->left)  clearTree(&tmpTree->left);

    if(tmpTree->right) clearTree(&tmpTree->right);
        
    free(tmpTree);

    *tree = NULL;
}



int main()
{
	int count, length;
	char temp;
	top = -1;
	printf("\nEnter an Expression in Infix format:\t");
	scanf("%[^\n]s", infix_string);
	infix_to_prefix();
	printf("\nExpression in Postfix Format: \t%s\n", postfix_string);
	length = strlen(postfix_string) - 1;
	strncpy(prefix_string, postfix_string, 20);
	for(count = 0; count < length; count++, length--)
	{
		temp = prefix_string[count];
		prefix_string[count] = prefix_string[length];
		prefix_string[length] = temp;
	}
	printf("\nExpression in Prefix Format: \t%s\n", prefix_string);
	negative();
	printf("\nExpression in Negative Prefix Format: \t%s\n", negative_prefix_string);

	// BUILD TREE
	// Construire racine 
	


    addNode(&Arbre, 'a',false);
    addNode(&Arbre, 'b',false);    
    addNode(&Arbre, 'c',true);


    puts("-------------------------------");

    printTree(Arbre);
	return 0;
}

void build_tree(char * form){

    	node *Arbre = NULL;
    	node *nodeLeft = NULL;
    	node *nodeRight = NULL;
	for(int i =0 ; i< strlen(form * sizeof(int));i++){
		// si c'est un "non" (-) , nous avons un fils
		if(form[i] == '-'){
		/* tu verifie que c'est le premier noeud */
			nodeLeft = addNode(&Arbre, form[i],true);
			i++;
			nodeLeft = addNode(&nodeLeft, form[i],true);
		/* si c'est */
		}else if((form =='&') || (form =='|') || (form =='>') || (form =='L') || (form =='M'))// si c'est un &,|,>,M,L
			/*
			Si from[i+i] est un carac, alors tu met form[i+1] a gauche et form[i+2] a droite
			Si c'est un autre ou/et/... tu fais 1 fils a gauche
			*/
		}else{ // si c'est une variable
			addNode();
		}
	
	}


}


void infix_to_prefix()
{
	unsigned int count, temp = 0;
	char next;
	char symbol;	
	for(count = 0; count < strlen(infix_string); count++)
	{
		symbol = infix_string[count];
		if(!checker(symbol))
		{
			switch(symbol)
			{
				case '(': push(symbol);
					  break;
				case ')':
					  while((next = pop()) != '(')
					  {
						postfix_string[temp++] = next;
					  }
					  break;
				case '&':
				case '|':
				case '-':
				case '>':
				case 'M':
				case 'L':
					  while(!isEmpty() && precedence(stack[top]) >= precedence(symbol))
					  postfix_string[temp++] = pop();
				          push(symbol);
					  break;
				default: 
			     		  postfix_string[temp++] = symbol;
			}
		}
	}
	while(!isEmpty()) 
	{
		postfix_string[temp++] = pop();
	}
	postfix_string[temp] = '\0';
}

int precedence(char symbol)
{
	switch(symbol)
	{
		case '(':
			 return 0;	
		case '&':
		case '|':
		case '>':
			 return 2;
		case '-':
		case 'M':
		case 'L':
			 return 3;
		default:
			 return 0;
	}
}

int checker(char symbol)
{
	if(symbol == '\t' || symbol == ' ')
	{
		return 1;
	}
	else
	{
		return 0;
	}
}

void push(long int symbol)
{
	if(top > 20)
	{
		printf("Stack Overflow\n");
		exit(1);
	}
	top = top + 1;
	stack[top] = symbol;
}

int isEmpty()
{
	if(top == -1)
	{
		return 1;
	}
	else
	{
		return 0;
	}
}

int pop()
{
	if(isEmpty())
	{
		printf("Stack is Empty\n");
		exit(1);
	}
	return(stack[top--]);
}

void negative(){
	negative_prefix_string[0] = '-';
	strcat(negative_prefix_string,prefix_string);
}

char* get_rules_K(int index){
	switch(index)
	{
		case '1':	// --A
			return "A";
		case '2':	// &AB
			return "X:A X:B";
		case '3':	// -|AB
			return "X:-A X:-B";
		case '4':	// ->AB
			return	"X:A X:-B";
		
		default: 
	     		  return "No rules";
	}
}

int find_rules(char* form){

	

}

void analyze_node(char* elements){

}










