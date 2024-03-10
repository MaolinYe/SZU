# multiAgents.py
# --------------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


from util import manhattanDistance
from game import Directions
import random, util
from math import exp
from game import Agent


class ReflexAgent(Agent):
    """
    A reflex agent chooses an action at each choice point by examining
    its alternatives via a state evaluation function.

    The code below is provided as a guide.  You are welcome to change
    it in any way you see fit, so long as you don't touch our method
    headers.
    """

    def getAction(self, gameState):
        """
        You do not need to change this method, but you're welcome to.

        getAction chooses among the best options according to the evaluation function.

        getAction takes a GameState and returns some Directions.X for some X in the set {NORTH, SOUTH, WEST, EAST, STOP}
        """
        # Collect legal moves and successor states
        legalMoves = gameState.getLegalActions()

        # Choose one of the best actions
        scores = [self.evaluationFunction(gameState, action) for action in legalMoves]
        bestScore = max(scores)
        bestIndices = [index for index in range(len(scores)) if scores[index] == bestScore]
        chosenIndex = random.choice(bestIndices)  # Pick randomly among the best

        "Add more of your code here if you want to"

        return legalMoves[chosenIndex]

    def evaluationFunction(self, currentGameState, action):
        """
        Design a better evaluation function here.

        The evaluation function takes in the current and proposed successor
        GameStates (pacman.py) and returns a number, where higher numbers are better.

        The code below extracts some useful information from the state, like the
        remaining food (newFood) and Pacman position after moving (newPos).
        newScaredTimes holds the number of moves that each ghost will remain
        scared because of Pacman having eaten a power pellet.

        Print out these variables to see what you're getting, then combine them
        to create a masterful evaluation function.
        """
        # Useful information you can extract from a GameState (pacman.py)
        successorGameState = currentGameState.generatePacmanSuccessor(action)
        newPos = successorGameState.getPacmanPosition()
        newFood = successorGameState.getFood()
        newGhostStates = successorGameState.getGhostStates()
        newScaredTimes = [ghostState.scaredTimer for ghostState in newGhostStates]

        "*** YOUR CODE HERE ***"
        ghost_x, ghost_y = successorGameState.getGhostPosition(1)  # ghost's position
        pacman_x, pacman_y = newPos  # pacman 's position
        death_distance = abs(ghost_x - pacman_x) + abs(ghost_y - pacman_y)  # the distance between ghost and pacman
        if action == 'Stop' or death_distance < 2:  # Stop is forbidden and distance less than 2 is safe
            return float('-inf')
        if newFood[pacman_x][pacman_y]:  # here is food
            return float('inf')
        open = [newPos]  # using BFS to find food
        visited = []
        while open:
            x, y = open.pop(0)
            if (x,y) in visited or x < 0 or y < 0 or x >= newFood.width or y >= newFood.height:  # out of range or visited
                continue
            if newFood[x][y]:  # food is found
                return exp(-(abs(pacman_x - x) + abs(pacman_y - y))) + successorGameState.getScore()
            visited.append((x, y))
            open = open + [(x - 1, y), (x, y - 1), (x + 1, y), (x, y + 1)]
        return successorGameState.getScore()


def scoreEvaluationFunction(currentGameState):
    """
    This default evaluation function just returns the score of the state.
    The score is the same one displayed in the Pacman GUI.

    This evaluation function is meant for use with adversarial search agents
    (not reflex agents).
    """
    return currentGameState.getScore()


class MultiAgentSearchAgent(Agent):
    """
    This class provides some common elements to all of your
    multi-agent searchers.  Any methods defined here will be available
    to the MinimaxPacmanAgent, AlphaBetaPacmanAgent & ExpectimaxPacmanAgent.

    You *do not* need to make any changes here, but you can if you want to
    add functionality to all your adversarial search agents.  Please do not
    remove anything, however.

    Note: this is an abstract class: one that should not be instantiated.  It's
    only partially specified, and designed to be extended.  Agent (game.py)
    is another abstract class.
    """

    def __init__(self, evalFn='scoreEvaluationFunction', depth='2'):
        self.index = 0  # Pacman is always agent index 0
        self.evaluationFunction = util.lookup(evalFn, globals())
        self.depth = int(depth)


class MinimaxAgent(MultiAgentSearchAgent):
    """
    Your minimax agent (question 2)
    """

    def getAction(self, gameState):
        """
        Returns the minimax action from the current gameState using self.depth
        and self.evaluationFunction.

        Here are some method calls that might be useful when implementing minimax.

        gameState.getLegalActions(agentIndex):
        Returns a list of legal actions for an agent
        agentIndex=0 means Pacman, ghosts are >= 1

        gameState.generateSuccessor(agentIndex, action):
        Returns the successor game state after an agent takes an action

        gameState.getNumAgents():
        Returns the total number of agents in the game

        gameState.isWin():
        Returns whether or not the game state is a winning state

        gameState.isLose():
        Returns whether or not the game state is a losing state
        """
        "*** YOUR CODE HERE ***"
        legalMoves = gameState.getLegalActions()
        scores = [self.min_value(gameState.generateSuccessor(0, action), 1, 0) for action in
                  legalMoves]  # Choose one of the best actions
        bestScore = max(scores)
        bestIndices = [index for index in range(len(scores)) if scores[index] == bestScore]
        chosenIndex = random.choice(bestIndices)  # Pick randomly among the best
        return legalMoves[chosenIndex]

    def max_value(self, gameState, agentIndex, depth):
        if depth >= self.depth or gameState.isWin() or gameState.isLose():
            return self.evaluationFunction(gameState)
        v = float('-inf')
        actions = gameState.getLegalActions(agentIndex)
        for action in actions:
            v = max(v, self.min_value(gameState.generateSuccessor(0, action), agentIndex + 1, depth + 1))
        return v

    def min_value(self, gameState, agentIndex, depth):
        if depth >= self.depth or gameState.isWin() or gameState.isLose():
            return self.evaluationFunction(gameState)
        v = float('inf')
        actions = gameState.getLegalActions(agentIndex)
        for action in actions:
            if agentIndex + 1 == gameState.getNumAgents():
                v = min(v, self.max_value(gameState.generateSuccessor(agentIndex, action), 0, depth + 1))
            else:
                v = min(v, self.min_value(gameState.generateSuccessor(agentIndex, action), agentIndex + 1, depth))
        return v


class AlphaBetaAgent(MultiAgentSearchAgent):
    """
    Your minimax agent with alpha-beta pruning (question 3)
    """

    def getAction(self, gameState):
        """
        Returns the minimax action using self.depth and self.evaluationFunction
        """
        "*** YOUR CODE HERE ***"
        legalMoves = gameState.getLegalActions()
        scores = [self.min_value(gameState.generateSuccessor(0, action), 1, 0, float('-inf'), float('inf')) for action
                  in
                  legalMoves]  # Choose one of the best actions
        bestScore = max(scores)
        bestIndices = [index for index in range(len(scores)) if scores[index] == bestScore]
        chosenIndex = random.choice(bestIndices)  # Pick randomly among the best
        return legalMoves[chosenIndex]

    def max_value(self, gameState, agentIndex, depth, alpha, beta):
        if depth >= self.depth or gameState.isWin() or gameState.isLose():
            return self.evaluationFunction(gameState)
        v = float('-inf')
        actions = gameState.getLegalActions(agentIndex)
        for action in actions:
            v = max(v, self.min_value(gameState.generateSuccessor(0, action), agentIndex+1, depth + 1, alpha, beta))
            if v > beta:
                return v
            alpha = max(alpha, v)
        return v

    def min_value(self, gameState, agentIndex, depth, alpha, beta):
        if depth >= self.depth or gameState.isWin() or gameState.isLose():
            return self.evaluationFunction(gameState)
        v = float('inf')
        actions = gameState.getLegalActions(agentIndex)
        for action in actions:
            if agentIndex+1==gameState.getNumAgents():
                v = min(v, self.max_value(gameState.generateSuccessor(agentIndex, action), 0, depth + 1, alpha, beta))
            else:
                v = min(v, self.min_value(gameState.generateSuccessor(agentIndex, action), agentIndex+1, depth, alpha, beta))
            if v < alpha:
                return v
            beta = min(beta, v)
        return v


def betterEvaluationFunction(currentGameState):
    """
    Your extreme ghost-hunting, pellet-nabbing, food-gobbling, unstoppable
    evaluation function (question 4).

    DESCRIPTION: <write something here so we know what you did>
    """
    "*** YOUR CODE HERE ***"
    util.raiseNotDefined()


# Abbreviation
better = betterEvaluationFunction
