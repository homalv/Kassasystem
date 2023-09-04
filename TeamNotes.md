### TEAM NOTES

```
This markdown file is for sharing notes and ideas for the team. Feel free to add sections as needed.
```

#### REMEMBER: 
* Project and course primary purpose - TESTING!
* git fetch BEFORE git push (to avoid conflicts)
* Commit often (locally)
  * Small commits are easier to review and backtrack 
  * Read the guide for "The perfect commit"
* ALWAYS USE LATEST VERSIONS OF EVERYTHING!

### Project Plan

```

1. TDD → Design / Implementation 

Team 1 
Directed 
Weighted

Team 2
Undirected 
Unweighted

2. Testing: 

Teams switch artifacts and test each others
Team 1 write tests for Team 2 classes 

3. TDD → Design / Implementation 

Team 1
BFSearcher (directedGraph)
Dijkstra (weightedGraph)

Team 2
DFSearcher (undirectedGraph)
A-star (unweightedGraph)

4. Testing: 
Teams switch artifacts and test each others

```

### Scrum 

```
Scrum master: Mr. Homero Alvarez
Scrum slaves: Everyone else

Weekly meeting → Mondays 9:45 (15 min???)
 * Sprint review
 * Sprint retrospective
 * Sprint planning

Daily standup → 9:30 (2 min / person)
 * Yesterday? 
 * Today? 
 * Problems? → Mediate, don't solve 
 
Backlog → Gitea + Discord Bot
 * Add to sprint backlog
 * Always claim an issue before working on it 

```


### Ideas

Section for adding things we want to do or try. Such as static testing tools added, addition to the build automation tool, etc.

* Add a static testing tool (PMD, FindBugs, Checkstyle, etc.)
* Configure the build automation tool to run the static testing tool
* Add a code coverage tool (JaCoCo, Cobertura, etc.)
* Configure the build automation tool to run the code coverage tool
* Integrate project's site documentation, which can be generated using mvn site.