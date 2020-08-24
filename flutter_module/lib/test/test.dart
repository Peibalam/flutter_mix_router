

import 'package:english_words/english_words.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter_module/test/stateful_test.dart';
import 'package:flutter_module/test/test_shop_car.dart';
import 'package:provider/provider.dart';

import '../init.dart';

void main() {

//  debugPaintSizeEnabled = true;
  runApp(MaterialApp(
    title: "my app",
    home: TutorialHome(),
  ));
}

class MyTestApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Startup Name Generator",
      home: RandomWords(),
    );
  }
}

class RandomWords extends StatefulWidget {

  @override
  _RandomWordsState createState() {
    return _RandomWordsState();
  }

}

class _RandomWordsState extends State<RandomWords> {

  final _suggestions = <WordPair>[];
  final _biggerFont = TextStyle(fontSize: 18.0);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Startup Name Generator'),
      ),
      body: _buildSuggestions(),
    );;
  }

  Widget _buildSuggestions() {
    return ListView.builder(
        itemBuilder: (context, i) {
          if (i.isOdd) return Divider();

          final index = i ~/ 2;

          if (index >= _suggestions.length) {
            _suggestions.addAll(generateWordPairs().take(10));
          }
          return _buildRow(_suggestions[index]);
        });
  }

  Widget _buildRow(WordPair pair) {
    return ListTile(
      title: Text(
        pair.asPascalCase,
        style: _biggerFont,
      ),
    );
  }

}


class MyAppBar extends StatelessWidget {

  MyAppBar({this.title});

  final Widget title;

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 56.0,
      padding: const EdgeInsets.symmetric(horizontal: 24.0),
      decoration: BoxDecoration(color: Colors.blue[500]),
      child: Row(
        children: <Widget>[
          IconButton(
            icon: Icon(Icons.menu),
            tooltip: 'Navigation menu',
            onPressed: null,
          ),
          Expanded(
            child: title,
          ),
          IconButton(
            icon: Icon(Icons.search),
            tooltip: 'search',
            onPressed: null,
          )
        ],
      ),
    );
  }
}

class MyScaffold extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Material(
      child: Column(
        children: <Widget>[
          MyAppBar(
            title: Text(
              'Example title',
              style: Theme.of(context).primaryTextTheme.headline6,
            ),
          ),
          Expanded(
            child: Center(
              child: Text('Hello, world!'),
            ),
          )
        ],
      ),
    );
  }
}


class TutorialHome extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: TestCounter(),
        title: Text('Example title'),
        actions: <Widget>[
          IconButton(
            icon: Icon(Icons.search),
            tooltip: 'search',
            onPressed: null,
          )
        ],
      ),
      body: Center(
        child: ChangeNotifierProvider(
          create: (context) => CountModel(),
          child: TestStateStatelessCounter(),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        tooltip: 'add',
        child: Icon(Icons.add),
        onPressed:  () {
          throw Exception("test throw error");
        },
      ),
    );
  }
}

class MyButton extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        print('MyButton was tapped!');
      },
      child: Container(
        height: 36.0,
        padding: const EdgeInsets.all(8.0),
        margin: const EdgeInsets.symmetric(horizontal: 8.0),
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(5.0),
          color: Colors.lightGreen[500],
        ),
        child: Center(
          child: Text('Engage'),
        ),
      ),
    );
  }
}

class InfoWidget extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    return Container(
      padding: EdgeInsets.all(20),
      child: Column(
        children: <Widget>[
          MyStar(),
          StarDescWidget()
        ],
      ),
    );
  }

}

class MyStar extends StatelessWidget {


  @override
  Widget build(BuildContext context) {

    var stars = Row(
      mainAxisSize: MainAxisSize.min,
      children: <Widget>[
        Icon(Icons.star, color: Colors.green[500]),
        Container(
          padding: EdgeInsets.fromLTRB(10, 30, 10, 30),
          child: Icon(Icons.star, color: Colors.green[500], size: 60),
        ),
        Icon(Icons.star, color: Colors.green[500]),
        Icon(Icons.star, color: Colors.green[500]),
        Icon(Icons.star, color: Colors.black)
      ],
    );

    return Container(
      padding: EdgeInsets.all(20),
      child: Row(
        children: <Widget>[
          stars,
          Text('170 Reviews',
              style: TextStyle(
                  color: Colors.black,
                  fontWeight: FontWeight.w800,
                  fontFamily: 'Roboto',
                  letterSpacing: 0.5,
                  fontSize: 20
              ))
        ],
      ),
    );

  }

}

class StarDescWidget extends StatelessWidget {

  final descTextStyle = TextStyle(
    color: Colors.black,
    fontWeight: FontWeight.w800,
    fontFamily: 'Roboto',
    letterSpacing: 0.5,
    fontSize: 18,
    height: 2
  );

  @override
  Widget build(BuildContext context) {
    return DefaultTextStyle.merge(
      style: descTextStyle,
      child: Container(
        padding: EdgeInsets.all(20),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            Column(
              children: <Widget>[
                Icon(Icons.kitchen, color: Colors.green[500]),
                Text('PREP:'),
                Text('25 min'),
              ],
            ),
            Column(
              children: <Widget>[
                Icon(Icons.timer, color: Colors.green[500]),
                Text('COOK:'),
                Text('1 hr'),
              ],
            ),
            Column(
              children: <Widget>[
                Icon(Icons.restaurant, color: Colors.green[500]),
                Text('FEEDS:'),
                Text('4-6'),
              ],
            )
          ],
        ),
      )
    );
  }
}
